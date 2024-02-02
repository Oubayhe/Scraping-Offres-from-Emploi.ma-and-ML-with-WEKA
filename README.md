# KHEDIMA - Scrap & ML

This project is designed to scrape job offers from Emploi.ma and store the data either in a MySQL database or as an Excel file. Two separate files handle the scraping process, each tailored for a specific method of data storage.

The project also includes a machine learning module powered by Weka. This module trains on the scraped data and performs testing using a subset of the dataset, and it can predict the experience level of a job offer.

## Scraping and Saving 
### To the DataBase:
* Customize the configurations in **ConnectionManager.java** in src/main/java/system
* Run **ScrapAndSaveToDB.java** 
> [!WARNING]
> in line 44 it's better to keep the gap low, something like 2 -> 5. Because Emploi has is very sensitive, it blocks anyone who trying to scrap offers for few hours to about 3 days.
### To an Excel File:
* Run **ScrapAndSaveToExcel.java**

## Machine Learning with WEKA
The files for training (TrainingData.java) and testing (testingData.java), may not super efficient for your current, but they are enough for undertand each classifier, RandomForest and J48, and view the efficiency of each one.
But it's better to use your own scrapped data, and split it to traning and testing one.

After Training the module can predict the Experience Level of each offer.
- **RandomForest**: *AlgoTwo.java*
- **J48**: *anotherML.java*

> [!NOTE]
> This repository is part of a larger project built in JAVA, featuring a user interface and integration with over three recruiting websites for scraping job offers. For more information and to explore the complete project, visit the [Scrap Job Offers and ML](https://github.com/Mouadspace/job-scraper) repository.