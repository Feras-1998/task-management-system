package com.feras.task.management.constant;

import com.feras.task.management.model.Task;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;

import java.util.List;

public enum OrderByTask {
    ID_ASC {
        @Override
        public void order(CriteriaBuilder criteriaBuilder, Root<Task> root, List<Order> orderList) {
            orderList.add(criteriaBuilder.asc(root.get("id")));
        }
    },

    TITLE_ASC {
        @Override
        public void order(CriteriaBuilder criteriaBuilder, Root<Task> root, List<Order> orderList) {
            orderList.add(criteriaBuilder.asc(root.get("title")));
        }
    },

    DESCRIPTION_ASC {
        @Override
        public void order(CriteriaBuilder criteriaBuilder, Root<Task> root, List<Order> orderList) {
            orderList.add(criteriaBuilder.asc(root.get("description")));
        }
    },

    DUE_DATE_ASC {
        @Override
        public void order(CriteriaBuilder criteriaBuilder, Root<Task> root, List<Order> orderList) {
            orderList.add(criteriaBuilder.asc(root.get("dueDate")));
        }
    },

    STATUS_ASC {
        @Override
        public void order(CriteriaBuilder criteriaBuilder, Root<Task> root, List<Order> orderList) {
            orderList.add(criteriaBuilder.asc(root.get("status")));
        }
    },

    ID_DESC {
        @Override
        public void order(CriteriaBuilder criteriaBuilder, Root<Task> root, List<Order> orderList) {
            orderList.add(criteriaBuilder.desc(root.get("id")));
        }
    },

    TITLE_DESC {
        @Override
        public void order(CriteriaBuilder criteriaBuilder, Root<Task> root, List<Order> orderList) {
            orderList.add(criteriaBuilder.desc(root.get("title")));
        }
    },

    DESCRIPTION_DESC {
        @Override
        public void order(CriteriaBuilder criteriaBuilder, Root<Task> root, List<Order> orderList) {
            orderList.add(criteriaBuilder.desc(root.get("description")));
        }
    },

    DUE_DATE_DESC {
        @Override
        public void order(CriteriaBuilder criteriaBuilder, Root<Task> root, List<Order> orderList) {
            orderList.add(criteriaBuilder.desc(root.get("dueDate")));
        }
    },

    STATUS_DESC {
        @Override
        public void order(CriteriaBuilder criteriaBuilder, Root<Task> root, List<Order> orderList) {
            orderList.add(criteriaBuilder.desc(root.get("status")));
        }
    };

    public abstract void order(CriteriaBuilder criteriaBuilder, Root<Task> root, List<Order> orderList);
}
