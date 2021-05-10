package com.example.mylocation;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

public class Stories extends BaseActivity {

    private Toolbar mTopToolbar;
    private RecyclerView recyclerView;
    private TextView text;
    private StoryItemAdapter adapter;
    private List<StoryItem> storyItems;
    private String[] stories = {"أنا خالد منذ الصغر كنت أشعر بالوحدة وعدم الانتماء للمجتمع لكن بعدما أصبح لدي اسرة صديقة تستضيفني كل نهاية أسبوع لإمضاء الوقت معهم والاستمتاع والشعور بجو الأسرة، من ثم أصبحت أشعر بالانتماء للمجتمع واطمح لأن أكون فرداً فعالاً في المستقبل",
                                "أنا فاتن توقعت أن أنجح لكن ليس بهذه السرعة، فمنذ أن كنت طفلة صغيرة كان لدي الطموح، كل من في الجمعية يعرف عني هذا. فانا لا أحب أن أكون انسانه عادية، وكنت اطمح دائما لأن أكون مميزة في عمل ما في مجتمعي وبين اصحابي، وضعت الهدف وبمساعدة التبرعات تمكنت من الوصل إليه والحمد الله.",
                                "انا رامي لقد تم توفير أجهزة كمبيوتر للأيتام من التبرعات ومن ثم بدأت أتعلم انا وأصدقائي على الكمبيوتر ومن ثم تطورت مهاراتنا وأصحبت أحب البرمجة وشغوف جداً بتعلمها وكل يوم اتعلم شيء جديد واطمح لأن أكمل دراستي في مجال البرمجة. ",
                                "أنا نادية منذ الصغر كنت أشعر بميلي إلى عالم التجميل، وعندما تعلمته وبدأت العمل فيه، أصبحت أكثر تواصلاً مع المجتمع وأكثر إيجابية مع الناس وركزت على هذا المجال وقررت السير فيه وبفضل التبرعات توفرت لي الفرصة أن أكمل مسار حياتي فيه."};
    private int[] img = {R.drawable.boy
                        , R.drawable.girltwo
                        , R.drawable.boy
                        , R.drawable.girltwo};
    private String [] names = {"خالد","فاتن","رامي","نادية"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strories);
        mTopToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        initCollapsingToolbar();
        recyclerView = (RecyclerView) findViewById(R.id.story_recycler);
        storyItems = new ArrayList<>();
        prepareData();
        adapter = new StoryItemAdapter(this, storyItems);
        RecyclerView.LayoutManager vLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(vLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1,dpToPx(10),true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitleEnabled(true);
        collapsingToolbar.setTitle(" ");
        collapsingToolbar.setCollapsedTitleGravity(Gravity.END);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    collapsingToolbar.setTitleEnabled(true);
                    collapsingToolbar.setTitle(" ");
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitleEnabled(true);
                    collapsingToolbar.setTitle(" ");
                    collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.black));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitleEnabled(true);
                    collapsingToolbar.setTitle(" ");
                    collapsingToolbar.setCollapsedTitleGravity(Gravity.END);
                    isShow = false;
                }
            }
        });
    }


    private void prepareData(){
        StoryItem storyItem;
        for(int i=0;i<stories.length;i++){
            storyItem= new StoryItem(stories[i], img[i], names[i]);
            storyItems.add(storyItem);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(MySharedPreference.getString(Stories.this, Constants.keys.APP_LANGUAGE,"en").matches("en"))
            getMenuInflater().inflate(R.menu.menu_main, menu);
        else
            getMenuInflater().inflate(R.menu.menu_main_ar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorite) {
            Intent i = new Intent(this,MapsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}
