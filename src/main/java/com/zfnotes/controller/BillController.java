package com.zfnotes.controller;

import com.zfnotes.entities.Bill;
import com.zfnotes.entities.BillProvider;
import com.zfnotes.entities.Provider;
import com.zfnotes.mapper.BillMapper;
import com.zfnotes.mapper.ProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 账单的控制层
 */
@Controller
public class BillController {

    @Autowired
    private BillMapper billMapper;
    @Autowired
    private ProviderMapper providerMapper;

    @GetMapping("/bills")
    public String list(Model model, Bill bill) {

        List<BillProvider> bills = billMapper.getBill(bill);
        List<Provider> providers = providerMapper.getProvider(null);
        model.addAttribute("bills", bills);
        model.addAttribute("providers", providers);
        //搜索之后回显搜索关键字
        model.addAttribute("billName", bill.getBillName());
        model.addAttribute("pid", bill.getPid());
        model.addAttribute("pay", bill.getPay());
        return "bill/list";
    }

    /**
     * 如果没有传入type则进入view页，
     * 如果传入type=update则进入update页
     */
    @GetMapping("/bill/{bid}")
    public String view(Model model, @PathVariable("bid") Integer bid,
                       @RequestParam(value = "type", defaultValue = "view") String type) {
        BillProvider bill = billMapper.getBillById(bid);

        if ("update".equals(type)){
            model.addAttribute("providers", providerMapper.getProvider(null));
        }

        model.addAttribute("bill", bill);
        return "bill/"+type;
    }


    @PutMapping("/bill")
    public String update(Bill bill){
        billMapper.updateBill(bill);
        return "redirect:/bills";
    }

    /**
     * 访问添加页面
     * @return
     */
    @GetMapping("/bill")
    public String addView(Model model){
        model.addAttribute("providers", providerMapper.getProvider(null));
        return "bill/add";
    }

    /**
     * 添加
     * @return
     */
    @PostMapping("/bill")
    public String addBill(Bill bill){
        billMapper.addBill(bill);
        return "redirect:/bills";
    }

    @DeleteMapping("/bill/{bid}")
    public String deleteBill(@PathVariable("bid") Integer bid){
        billMapper.deleteBill(bid);
        return "redirect:/bills";
    }
}
