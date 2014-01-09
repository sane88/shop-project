package com.test.project.controller;

import com.test.project.exception.AuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Valentin
 */
@Controller
@RequestMapping("/item")
public class ItemController extends AbstractController{
    @RequestMapping(value = "buy", method = RequestMethod.POST)
    @ResponseBody
    public String buy(@RequestParam(required = true) Integer itemId){
        Authentication authentication = getAuthentication();
        checkAuthentication(authentication);
        String username = (String) authentication.getPrincipal();
        getService().addPurchasedItem(username, itemId);
        return "success";
    }

        @RequestMapping(value = "wish", method = RequestMethod.POST)
    @ResponseBody
    public String addToWishList(@RequestParam(required = true) Integer itemId) {
        Authentication authentication = getAuthentication();
        checkAuthentication(authentication);
        String username = (String) authentication.getPrincipal();
        getService().addWishedItem(username, itemId);
        return "success";
    }

    @RequestMapping(value = "unwish", method = RequestMethod.POST)
    @ResponseBody
    public String removeFromWishList(@RequestParam(required = true) Integer itemId){
        Authentication authentication = getAuthentication();
        String username = (String) authentication.getPrincipal();
        getService().removeFromWishlist(username, itemId);
        return "success";
    }


}
