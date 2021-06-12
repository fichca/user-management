package by.control.usermanagement.util;

import by.control.usermanagement.constant.Constants;
import by.control.usermanagement.entity.User;

import java.util.List;

public class PageUtil {

    public static List<User> subListUsersByPageNumber(List<User> users, int pageNumber){
        if (users.isEmpty()){
            return users;
        }
        int end = pageNumber;
        for (int i = pageNumber; i < users.size(); i++) {
            if (end == pageNumber + Constants.USERS_ON_PAGE){
                break;
            }
            end++;
        }
        return users.subList(pageNumber, end);
    }

    public static int getCountUsersPage(List<User> users) {
        if (users.isEmpty()){
            return 1;
        }
        return ((users.size() - 1) / Constants.USERS_ON_PAGE) + 1;
    }
}
