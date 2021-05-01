package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 원래는 @Component 인데 나눠서 쓰는 것 뿐 , 필요하면 Component 사용한다(자동 등록), ComponentScan(Spring)
@Controller
public class MemberController {

    // New를 선언해서 사용하는 것이 아닌, SpringContainer에서 가져와서 써야하는 형태가 맞다
    // 여러 컨트롤러에서 MemberService를 가져다 쓸수 있기 때문에(하나 생성해놓고 공용으로 사용)
    // private final MemberService memberService = new MemberService();
    private final MemberService memberService;

    // 생성자에 Autowired -> Container의 memberService Spring이 가지고 와서 연결 시켜준다 -> DI(스프링 시작 할때 Insection)
// 의존 관계가 실행중 동적으로 변하는 경우는 거의 없기 때문에
// [생성자 주입(가장 추천)] - 1
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

// [필드 주입] - 2
//    @Autowired private MemberService memberService; // 중간에 바꿔치기 할 수 단이 없기 때문에 추천되지 않음

// [세터 주입] - 3
//    private MemberService memberService;    // setter를 굳이 public 할 이유가 없는데 노출이 되버린다
//
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {  // 이쪽으로 html의 name 값이 들어온다
        Member member = new Member();
        member.setName(form.getName()); // private 이라 함부로 접근 못한다

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
