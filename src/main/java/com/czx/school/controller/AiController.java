package com.czx.school.controller;

import com.czx.school.common.Response;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: ai聊天接口
 * @date: 2026/1/15
 */
@RestController
@RequestMapping("/ai")
public class AiController {
    @Autowired
    private QwenChatModel qwenChatModel;

    @PostMapping("/chat")
    public Response<String> chat(@RequestParam String question) {
        String chat = qwenChatModel.chat(question);
        return Response.success("问题发送成功",chat);
    }
}
