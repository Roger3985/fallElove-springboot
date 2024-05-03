package com.roger.columnarticle.controller;

import com.ren.administrator.entity.Administrator;
import com.roger.columnarticle.entity.ColumnArticle;
import com.roger.columnarticle.service.ColumnArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/backend/columnarticle")
public class ColumnArticleControllerBackEnd {

    @Autowired
    ColumnArticleService columnArticleService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 處理列出所有專欄文章的請求。
     * 此方法處理對 '/backend/columnarticle/listAllColumnArticle' 的 GET 請求，並返回顯示所有專欄文章的視圖。
     *
     * @param model 用於存儲和傳遞資料到視圖的模型物件。
     * @return `/backend/columnarticle/listAllColumnArticle` 視圖名稱，以顯示所有專欄文章的列表。
     */
    @GetMapping("/listAllColumnArticle")
    public String listAllColumnArticle(Model model) {
        return "/backend/columnarticle/listAllColumnArticle";
    }

    /**
     * 提供所有專欄文章資料列表供視圖渲染使用。
     * 此方法使用`@ModelAttribute`註解，確保在處理請求時可用於視圖中的`columnArticleListData`屬性。
     * 該屬性是一個包含所有專欄文章的列表，由`columnArticleService.findAll()`方法獲取。
     *
     * @return 包含所有專欄文章的列表。
     */
    @ModelAttribute("columnArticleListData")
    protected List<ColumnArticle> referenceListData() {
        List<ColumnArticle> list = columnArticleService.findAll();
        return list;
    }

    // 前往專欄文章修改頁面
    @GetMapping("/updateColumnArticleData")
    public String updateColumnArticleData(ModelMap modelMap, HttpSession session) {

        // 從會話中獲取當前登錄的管理員
        Administrator administrator = (Administrator) session.getAttribute("ValidAdministrator");

        return "1";
    }





}
