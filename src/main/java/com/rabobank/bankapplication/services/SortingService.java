package com.rabobank.bankapplication.services;

import com.rabobank.bankapplication.models.Transaction;

import java.util.*;

public class SortingService {


    public enum Category {
        Energy, General, DrugStores, Furniture, Groceries, Restaurant,
        PublicTransport, Trains, Flights, Clothing, Other

    }

    public static Category categorize(Transaction transaction) {
        String targetCategory = transaction.getDescription();
        HashMap<Category, List<String>> categoryMapping = getCategoriesListHashMap();

        return categoryMapping.entrySet().stream()
                .filter(entry -> entry.getValue().contains(targetCategory.toLowerCase(Locale.ROOT)))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(Category.Other);
    }

    private static HashMap<Category, List<String>> getCategoriesListHashMap() {
        HashMap<Category, List<String>> categoryMapping = new HashMap<>();
        categoryMapping.put(Category.Clothing, Arrays.asList("vintageisland", "societyshop", "blackfish", "blundstone", "hunkemoller"));
        categoryMapping.put(Category.Groceries, Arrays.asList("ah", "jumbo", "coop", "marqt", "tesco", "m&s", "waitrose"));
        categoryMapping.put(Category.Flights, Arrays.asList("easyjet", "transavia","wizzair", "ryanair", "klm", "canada air"));
        categoryMapping.put(Category.Energy, Arrays.asList("ista","eneco","engie","greenchoice"));
        categoryMapping.put(Category.Furniture, Arrays.asList("ikea", "jysk", "westelm"));
        categoryMapping.put(Category.Restaurant, Arrays.asList("lapsang","ñ", "pastis","latabla","levantini"));
        categoryMapping.put(Category.Trains, Arrays.asList("ns","arriva", "db","eurostar"));
        return categoryMapping;
    }

}




