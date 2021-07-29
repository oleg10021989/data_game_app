package com.example.gamedataapp;

public class DataModel2 {

    String ID , url , types , game_name , game_description , release_date , developer , publisher , popular_tags , languages , genre , minimum_requirements , recommended_requirements , original_price,img_utl;




    public DataModel2(String ID, String url, String types, String game_name, String game_description, String release_date, String developer, String publisher, String popular_tags, String languages, String genre, String minimum_requirements, String recommended_requirements, String original_price,String img_utl) {
        this.ID = ID;
        this.url = url;
        this.types = types;
        this.game_name = game_name;
        this.game_description = game_description;
        this.release_date = release_date;
        this.developer = developer;
        this.publisher = publisher;
        this.popular_tags = popular_tags;
        this.languages = languages;
        this.genre = genre;
        this.minimum_requirements = minimum_requirements;
        this.recommended_requirements = recommended_requirements;
        this.original_price = original_price;
        this.img_utl=img_utl;
    }

    public String getID() {
        return ID;
    }

    public String getUrl() {
        return url;
    }

    public String getTypes() {
        return types;
    }

    public String getGame_name() {
        return game_name;
    }

    public String getGame_description() {
        return game_description;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPopular_tags() {
        return popular_tags;
    }

    public String getLanguages() {
        return languages;
    }

    public String getGenre() {
        return genre;
    }

    public String getMinimum_requirements() {
        return minimum_requirements;
    }

    public String getRecommended_requirements() {
        return recommended_requirements;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public String getImg_utl(){return img_utl; }



}
