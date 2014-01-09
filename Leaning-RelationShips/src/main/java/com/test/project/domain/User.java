package com.test.project.domain;

/**
 * @author Valentin
 */

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Table(name = "USERS")
public class User implements Serializable {
    private String username;
    private String password;
    private String role;
    private Collection<Item> purchasedItems;
    private Collection<Item> wishList;

    public User() {
    }

    @Id
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    @Column
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany( cascade = CascadeType.ALL)
    @JoinTable(name = "BOUGHT_ITEMS")
    public Collection<Item> getPurchasedItems() {
        return purchasedItems;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "WISH_LIST")
    public Collection<Item> getWishList() {
        return wishList;
    }

    public void setWishList(Collection<Item> wishlist) {
        this.wishList = wishlist;
    }

    public void setPurchasedItems(Collection<Item> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }

    public void addPurchasedItem(Item item) {
        if(purchasedItems == null){
            purchasedItems = new ArrayList<Item>();
        }
        purchasedItems.add(item);
    }

    public void addWishedItem(Item item) {
        if(wishList == null){
            wishList = new ArrayList<Item>();
        }
        wishList.add(item);
    }

    public void removeWishedItem(Item item){
        getWishList().remove(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!password.equals(user.password)) return false;
        if (!role.equals(user.role)) return false;
        if (!username.equals(user.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}