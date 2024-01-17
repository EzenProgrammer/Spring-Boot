package org.spring.ch3.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;
import lombok.extern.log4j.Log4j2;
import org.spring.ch3.dto.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {
  @GetMapping("/ex1")
  public void ex1() {
    log.info("ex1..........");
  }

  //  @GetMapping({"/ex2"})
  //  public void ex2(Model model) {
  //    log.info("ex..........");
  //    SampleDTO dto = SampleDTO.builder().regTime(LocalDateTime.now()).build();
  //    List<SampleDTO> list =
  //        IntStream.rangeClosed(1, 20)
  //            .asLongStream()
  //            .mapToObj(
  //                i -> dto.toBuilder().sno(i).first("First...." + i).last("Last..." + i).build())
  //            .toList();
  //    log.info(list);
  //    model.addAttribute("list", list);
  //  }

  @GetMapping("/exInline")
  public String exInline(RedirectAttributes redirectAttributes) {
    log.info("exInline.......................");
    SampleDTO dto =
        SampleDTO.builder()
            .sno(100L)
            .first("First..100")
            .last("last..100")
            .regTime(LocalDateTime.now())
            .build();
    redirectAttributes.addFlashAttribute("result", "success");
    redirectAttributes.addFlashAttribute("dto", dto);
    return "redirect:/sample/ex3";
  }

  @GetMapping("/ex3")
  public void ex3() {
    log.info("ex3.......................");
  }

  @GetMapping({"/ex2", "/exLink"})
  public void exModel(Model model) {
    List<SampleDTO> list =
        IntStream.rangeClosed(1, 20)
            .asLongStream()
            .mapToObj(
                i ->
                    SampleDTO.builder()
                        .sno(i)
                        .first("First")
                        .last("Last")
                        .regTime(LocalDateTime.now())
                        .build())
            .toList();
    model.addAttribute("list", list);
  }

  @GetMapping({"/exLayout1", "/exLayout2", "/exTemplate", "/exSidebar"})
  public void exLayout() {
    log.info("exLayout...........");
  }
}