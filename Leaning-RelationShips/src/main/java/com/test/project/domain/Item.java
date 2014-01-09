package com.test.project.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * @author Valentin
 */

@Entity
@Table(name = "ITEMS")
public class Item implements Serializable{
    private int itemId;
    private String name;
    private String description;
    private String imageUrl;
    private Set<User> usersBoughtIt;
    private Set<User> usersWishIt;

    @Id
    @GeneratedValue
    @Column()
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Column()
    public String getDescription() {
        return description;
    }

    public void setDescription(String itemDescr) {
        this.description = itemDescr;
    }

    @ManyToMany(mappedBy = "purchasedItems", cascade = CascadeType.ALL)
    public Set<User> getUsersBoughtIt() {
        return usersBoughtIt;
    }

    public void setUsersBoughtIt(Set<User> users) {
        this.usersBoughtIt = users;
    }

    @ManyToMany(mappedBy = "wishList", cascade = CascadeType.ALL)
    public Set<User> getUsersWishIt() {
        return usersWishIt;
    }

    public void setUsersWishIt(Set<User> usersWishIs) {
        this.usersWishIt = usersWishIs;
    }

    @Column
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String itemName) {
        this.name = itemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (itemId != item.itemId) return false;
        if (description != null ? !description.equals(item.description) : item.description != null) return false;
        if (imageUrl != null ? !imageUrl.equals(item.imageUrl) : item.imageUrl != null) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (usersBoughtIt != null ? usersBoughtIt.hashCode() : 0);
        result = 31 * result + (usersWishIt != null ? usersWishIt.hashCode() : 0);
        return result;
    }
}
