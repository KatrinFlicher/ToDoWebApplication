package by.gsu.epamlab.beans;

public class User {
    private String login;
    private Role role;
    private String name;
    private String email;

    public User(String login, Role role, String name, String email) {
        this.login = login;
        this.role = role;
        this.name = name;
        this.email = email;
    }

    public User(String login, String name, String email) {
        this.login = login;
        this.name = name;
        this.email = email;
    }

    public User() {
    }

    public User(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return getLogin().equals(user.getLogin());
    }

    @Override
    public int hashCode() {
        return getLogin().hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
