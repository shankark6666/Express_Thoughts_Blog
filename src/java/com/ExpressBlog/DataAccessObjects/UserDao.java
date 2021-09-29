package com.ExpressBlog.DataAccessObjects;

import com.ExpressBlog.entities.Post;
import com.ExpressBlog.entities.User;
import java.sql.*;

public class UserDao {

    private Connection con;

    public UserDao(Connection con) {
        this.con = con;
    }

    // method to insert user data into databse
    public boolean saveUser(User user) {

        boolean f = false;
        try {

            String query = "insert into user(name,email,password,gender,about) values (?,?,?,?,?)";

            PreparedStatement pstmt = this.con.prepareStatement(query);

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getGender());
            pstmt.setString(5, user.getAbout());

            pstmt.executeUpdate();
            f = true;

        } catch (Exception e) {

            e.printStackTrace();

        }
        return f;
    }

    // get user  pass and email
    public User getUserByEmailAndPassword(String email, String password) {

        User user = null;
        try {

            String query = "select * from user where email=? and password=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet set = pstmt.executeQuery();

            if (set.next()) {

                user = new User();
//             data from db
                String name = set.getString("name");
//              set to user object
                user.setName(name);

                user.setId(set.getInt("id"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
                user.setGender(set.getString("gender"));
                user.setAbout(set.getString("about"));
                user.setDateTime(set.getTimestamp("rdate"));
                user.setProfile(set.getString("profile"));

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return user;

    }
    
    
    
    
     public boolean updateUser(User user) {

        boolean f = false;
        try {

            String query = "update user set name=? , email=? , password=? , gender=? ,about=? , profile=? where  id =?";
            PreparedStatement p = con.prepareStatement(query);
            p.setString(1, user.getName());
            p.setString(2, user.getEmail());
            p.setString(3, user.getPassword());
            p.setString(4, user.getGender());
            p.setString(5, user.getAbout());
            p.setString(6, user.getProfile());
            p.setInt(7, user.getId());

            p.executeUpdate();
            f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
     
     
     }
    


 public Post getPostByPostId(int postId) {
        Post post = null;
        String q = "select * from posts where pid=?";
        try {
            PreparedStatement p = this.con.prepareStatement(q);
            p.setInt(1, postId);

            ResultSet set = p.executeQuery();

            if (set.next()) {

                int pid = set.getInt("pid");
                String pTitle = set.getString("pTitle");
                String pContent = set.getString("pContent");
                String pCode = set.getString("pCode");
                String pPic = set.getString("pPic");
                Timestamp date = set.getTimestamp("pDate");
                int cid=set.getInt("catId");
                int userId = set.getInt("userId");
                post = new Post(pid, pTitle, pContent, pCode, pPic, date, cid, userId);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }




 public User getUserByUserId(int userId) {
        User user = null;
        try {
            String q = "select * from user where id=?";
            PreparedStatement ps = this.con.prepareStatement(q);
            ps.setInt(1, userId);
            ResultSet set = ps.executeQuery();
            if (set.next()) {
                user = new User();

//             data from db
                String name = set.getString("name");
//             set to user object
                user.setName(name);

                user.setId(set.getInt("id"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
                user.setGender(set.getString("gender"));
                user.setAbout(set.getString("about"));
                user.setDateTime(set.getTimestamp("rdate"));
                user.setProfile(set.getString("profile"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
