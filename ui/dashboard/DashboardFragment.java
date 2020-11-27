package com.example.testinggg.ui.dashboard;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.testinggg.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    TextView newsView1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //Set up the variables
        newsView1 = (TextView) root.findViewById(R.id.news1);
        newsView1.setMovementMethod(new ScrollingMovementMethod());

        //Get the news web scrape
        new getNews().execute();

        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    //Web scraper to return the top 10 news headliens
    public class getNews extends AsyncTask<Void, Void, Void> {
        String newsHeadline1;

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                //Sky news
                Document doc = Jsoup.connect("https://news.sky.com/uk").get();
                Elements headlines = doc.getElementsByClass("sdc-site-trending__item");

                //Arraylist each of the 10 headlines
                for (Element headline : headlines){
                    //if(headline.getElementsByClass("sdc-site-trending__link-text").text().contains(null)){
                        newsHeadline1 += headline.getElementsByClass("sdc-site-trending__item").text();
                        newsHeadline1 += "\n\n";
                    //}
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
            return null;
        }

        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            newsView1.setText(newsHeadline1);
        }

    }

}