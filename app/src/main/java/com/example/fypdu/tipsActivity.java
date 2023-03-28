package com.example.fypdu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class tipsActivity extends AppCompatActivity {

    ListView listview;
    List list =  new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        listview = findViewById(R.id.listView);
        list.add(" \n \n");
        list.add("Set financial goals: Determine what you want to achieve with your money in the short-term and long-term. This will help you prioritize your spending and focus on what matters most.\n" +
                "\n");
        list.add("Create a budget: Make a list of your income and expenses to determine how much money you have to work with each month. This will help you avoid overspending and keep track of where your money is going.\n" +
                "\n");
        list.add("Track your spending: Keep a record of all your expenses, including small purchases like coffee or snacks. This will help you identify areas where you can cut back on spending and save more money.\n" +
                "\n");
        list.add("Save for emergencies: Set aside a portion of your income each month for unexpected expenses, such as car repairs or medical bills. Aim to have at least three to six months' worth of living expenses in an emergency fund.\n" +
                "\n");
        list.add("Pay off high-interest debt: If you have credit card debt or loans with high interest rates, prioritize paying them off as soon as possible. This will save you money in the long run and improve your credit score.\n" +
                "\n");
        list.add("Invest for the future: Consider investing your money in stocks, bonds, or other assets that can grow in value over time. This can help you build wealth and achieve your long-term financial goals.\n" +
                "\n");
        list.add("Live within your means: Avoid overspending and living beyond your means. Stick to your budget and prioritize your spending based on your financial goals.\n" +
                "\n");
        list.add("Seek professional advice: Consider consulting a financial advisor or accountant for help with managing your money. They can provide expert guidance and help you make informed decisions about your finances.\n" +
                "\n");
        list.add("Avoid impulsive purchases: Think carefully before making large purchases, especially if they are not essential. Take the time to evaluate your options and make sure the purchase aligns with your financial goals.\n" +
                "\n");
        list.add("Stay informed: Keep up-to-date with the latest financial news and trends. This can help you make informed decisions about your investments and overall financial strategy.\n" +
                "\n");
        list.add("");


        adapter = new ArrayAdapter(tipsActivity.this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

    }
}