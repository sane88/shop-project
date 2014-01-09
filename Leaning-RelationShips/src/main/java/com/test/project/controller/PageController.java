package com.test.project.controller;

import com.test.project.domain.Item;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Collection;

/**
 * @author Valentin
 */
@Controller
@RequestMapping("/")
public class PageController extends AbstractController {


    public static final String INDEX = "index";

    @RequestMapping(value = "cart")
    public String cart(Model model){
        Authentication authentication = getAuthentication();
        checkAuthentication(authentication);
        String username = (String) authentication.getPrincipal();
        model.addAttribute("authorized", isAuthenticated(getAuthentication()));
        model.addAttribute("user", getAuthentication().getPrincipal());
        Collection<Item> purchasedItems = getService().getPurchasedItems(username);
        fillModelAttributes(model, purchasedItems, false, false, false, "My cart");
        return INDEX;
    }


    @RequestMapping(value = "wishlist")
    public String wishList(Model model){
        Authentication authentication = getAuthentication();
        checkAuthentication(authentication);
        String username = (String) authentication.getPrincipal();
        model.addAttribute("authorized", isAuthenticated(getAuthentication()));
        model.addAttribute("user", getAuthentication().getPrincipal());
        Collection<Item> wishedItems = getService().getWishList(username);
        fillModelAttributes(model, wishedItems, true, false, true, "My wishlist");
        return INDEX;
    }



    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("authorized", isAuthenticated(getAuthentication()));
        model.addAttribute("user", getAuthentication().getPrincipal());
        Collection<Item> items = getService().getAllItems();
        fillModelAttributes(model, items, true, true, false, "Product catalog");
        if("ADMIN".equals(getRole())){
            model.addAttribute("adminBtns", true);
        }
        return INDEX;
    }

    @RequestMapping(value = "login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "register")
    public String registrationPage(){
        return "registration";
    }

    private void fillModelAttributes(Model model, Collection<Item> items,
                                     boolean showBuyBtn, boolean showAddToWishBtn,
                                     boolean showRemoveFromWishBtn, String place){
        model.addAttribute("place", place);
        model.addAttribute("items", items);
        model.addAttribute("showBuyBtn", showBuyBtn);
        model.addAttribute("showAddToWishBtn", showAddToWishBtn);
        model.addAttribute("showRemoveFromWishBtn", showRemoveFromWishBtn);
    }

    private String getRole(){
        for (GrantedAuthority grantedAuthority : getAuthentication().getAuthorities()) {
            return grantedAuthority.getAuthority();
        }
        return null;
    }
}
