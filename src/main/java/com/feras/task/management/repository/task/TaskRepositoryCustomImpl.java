package com.feras.task.management.repository.task;

import com.feras.task.management.constant.OrderByTask;
import com.feras.task.management.dto.task.TaskFilter;
import com.feras.task.management.model.Task;
import com.feras.task.management.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class TaskRepositoryCustomImpl implements TaskRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Task> searchTasks(long userId, TaskFilter taskFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> query = criteriaBuilder.createQuery(Task.class);
        Root<Task> root = query.from(Task.class);

        prepareFilters(criteriaBuilder, query, root, userId, taskFilter);
        prepareOrderBy(criteriaBuilder, query, root, taskFilter.getOrderBy());

        return entityManager.createQuery(query).getResultList();
    }

    private void prepareFilters(CriteriaBuilder criteriaBuilder, CriteriaQuery<Task> query, Root<Task> root,
                                long userId, TaskFilter taskFilter) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(root.get("user").get("id"), userId));

        if (taskFilter.getSearchKey() != null && !taskFilter.getSearchKey().isEmpty()) {
            String searchKeyLower = taskFilter.getSearchKey().toLowerCase();
            Expression<String> concatExpression = criteriaBuilder.concat(
                    criteriaBuilder.lower(root.get("title")),
                    criteriaBuilder.concat("-", criteriaBuilder.lower(root.get("description")))
            );
            predicates.add(criteriaBuilder.like(concatExpression, "%" + searchKeyLower + "%"));
        }

        if (taskFilter.getDueDateFrom() > 0) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dueDate"), taskFilter.getDueDateFrom()));
        }

        if (taskFilter.getDueDateTo() > 0) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dueDate"), taskFilter.getDueDateTo()));
        }

        if (taskFilter.getStatus() != null && !taskFilter.getStatus().isEmpty()) {
            predicates.add(root.get("status").in(taskFilter.getStatus()));
        }

        query.multiselect(
                root.get("id"), root.get("title"), root.get("description"), root.get("dueDate"),
                root.get("status"), criteriaBuilder.nullLiteral(User.class)
        ).where(predicates.toArray(new Predicate[0]));
    }

    private void prepareOrderBy(CriteriaBuilder criteriaBuilder, CriteriaQuery<Task> query, Root<Task> root, Set<OrderByTask> orderBy) {
        if (orderBy != null && !orderBy.isEmpty()) {
            List<Order> orderList = new ArrayList<>();
            orderBy.forEach(orderByTask -> orderByTask.order(criteriaBuilder, root, orderList));
            query.orderBy(orderList);
        }
    }
}
