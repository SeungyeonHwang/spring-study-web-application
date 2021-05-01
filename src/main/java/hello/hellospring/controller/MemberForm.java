package hello.hellospring.controller;

// 이쪽으로 html의 name 값이 들어온다
public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
