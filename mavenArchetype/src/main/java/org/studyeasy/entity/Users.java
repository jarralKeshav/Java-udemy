    package org.studyeasy.entity;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.Id;
    import jakarta.persistence.Table;


    @Entity(name = "users")
    @Table(name = "users")
    public class Users {
        @Id
        @Column(name = "user_id")
        int userId;

        @Column(name = "username")
        String userName;

        @Column(name = "password")
        String password;

        @Column(name = "first_name")
        String firstname;

        @Column(name = "last_name")
        String lastname;

            public Users() {
            }

        public Users(String userName, String password, String firstname, String lastname) {
            this.userName = userName;
            this.password = password;
            this.firstname = firstname;
            this.lastname = lastname;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        @Override
        public String toString() {
            return "Users{" +
                    "userId=" + userId +
                    ", userName='" + userName + '\'' +
                    ", password='" + password + '\'' +
                    ", firstname='" + firstname + '\'' +
                    ", lastname='" + lastname + '\'' +
                    '}';
        }
    }
