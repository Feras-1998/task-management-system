package com.feras.task.management.dto.user;

import com.feras.task.management.utils.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestBody {
    private String username;
    private String password;

    public boolean isInValidData() {
        return Util.isEmpty(username) || Util.isEmpty(password);
    }
}
