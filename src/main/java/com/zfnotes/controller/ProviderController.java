package com.zfnotes.controller;

import com.zfnotes.entities.Provider;
import com.zfnotes.mapper.ProviderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 供应商的控制层
 */
@Controller
public class ProviderController {


    @Autowired
    private ProviderMapper providerMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/providers")
    public String list(Model model, Provider provider) {
        logger.info("供应商列表查询" + provider);

        List<Provider> providers = providerMapper.getProvider(provider);
        model.addAttribute("providers", providers);
        //搜索之后回显搜索关键字
        model.addAttribute("providerName", provider.getProviderName());
        return "provider/list";
    }

    /**
     * 如果没有传入type则进入view页，
     * 如果传入type=update则进入update页
     * @param model
     * @param pid
     * @param type
     * @return
     */
    @GetMapping("/provider/{pid}")
    public String view(Model model, @PathVariable("pid") Integer pid,
                       @RequestParam(value = "type", defaultValue = "view") String type) {

        logger.info("查询"+pid+"的供应商");
        Provider provider = providerMapper.getProviderById(pid);
        model.addAttribute("provider", provider);
        return "provider/"+type;
    }


    @PutMapping("/provider")
    public String update(Provider provider){
        logger.info("更新供应商信息");
        providerMapper.updateProvider(provider);
        return "redirect:/providers";
    }

    /**
     * 访问添加页面
     * @return
     */
    @GetMapping("/provider")
    public String addView(){
        return "provider/add";
    }

    /**
     * 添加
     * @return
     */
    @PostMapping("/provider")
    public String addProvider(Provider provider){
        providerMapper.addProvider(provider);
        return "redirect:/providers";
    }

    @DeleteMapping("/provider/{pid}")
    public String deleteProvider(@PathVariable("pid") Integer pid){
        providerMapper.deleteProviderById(pid);
        return "redirect:/providers";
    }

}

