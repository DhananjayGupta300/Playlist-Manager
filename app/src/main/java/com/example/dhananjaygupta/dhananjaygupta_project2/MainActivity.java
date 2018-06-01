package com.example.dhananjaygupta.dhananjaygupta_project2;

import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;



public class MainActivity extends AppCompatActivity implements AddDialog.DialogAddingListener {

    private ListView listView;

    CustomAdapter customAdapter =new CustomAdapter();
    ArrayList<String> SongTitle=new ArrayList<>();
    ArrayList<String> ArtistURL= new ArrayList<>();
    ArrayList<String> VideoUrl= new ArrayList<>();
    ArrayList<String> WikiURL = new ArrayList<>();
    ArrayList<String> Artist= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initialize();
        listView= (ListView)findViewById(R.id.list);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long it) {
                Intent intent = new Intent(getApplicationContext(),WebPage.class);
                intent.putExtra("url",VideoUrl.get(position)) ;
                intent.putExtra("title",SongTitle.get(position));
                startActivity(intent);
            }
        }) ;
        registerForContextMenu(listView);
    }
    public void Initialize(){
        SongTitle.add("Chaiya Chaiya");
        SongTitle.add("Fear Of The Dark");
        SongTitle.add("Tamally Maak");
        SongTitle.add("Hymn For The Weekend");
        SongTitle.add("Blood Brothers");
        Artist.add("A.R. Rahman");
        Artist.add("Iron Maiden");
        Artist.add("Amr Diab");
        Artist.add("Coldplay");
        Artist.add("IronMaiden");
        VideoUrl.add("https://www.youtube.com/watch?v=PQmrmVs10X8");
        VideoUrl.add("https://www.youtube.com/watch?v=J0N1yY937qg");
        VideoUrl.add("https://www.youtube.com/watch?v=EgmXTmj62ic");
        VideoUrl.add("https://www.youtube.com/watch?v=YykjpeuMNEk");
        VideoUrl.add("https://www.youtube.com/watch?v=MmAtwvZYTe8");
        WikiURL.add("https://en.wikipedia.org/wiki/Chaiyya_Chaiyya");
        WikiURL.add("https://en.wikipedia.org/wiki/Fear_of_the_Dark_(Iron_Maiden_album)");
        WikiURL.add("https://en.wikipedia.org/wiki/Tamally_Maak");
        WikiURL.add("https://en.wikipedia.org/wiki/Hymn_for_the_Weekend");
        WikiURL.add("https://en.wikipedia.org/wiki/Brave_New_World_(Iron_Maiden_album)");
        ArtistURL.add("https://en.wikipedia.org/wiki/A._R._Rahman");
        ArtistURL.add("https://en.wikipedia.org/wiki/Iron_Maiden");
        ArtistURL.add("https://en.wikipedia.org/wiki/Amr_Diab");
        ArtistURL.add("https://en.wikipedia.org/wiki/Coldplay");
        ArtistURL.add("https://en.wikipedia.org/wiki/Iron_Maiden");
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.remove);
        SubMenu subMenu = menuItem.getSubMenu();
        for(int i =0 ;i<SongTitle.size();i++){
            subMenu.add(0,i,i,SongTitle.get(i));
        }
        return true;
    }
    @Override
    public void songAdding(String song, String artist,String artisturl, String wikiurl, String videourl) {
        SongTitle.add(song);
        Artist.add(artist);
        ArtistURL.add(artisturl);
        WikiURL.add(wikiurl);
        VideoUrl.add(videourl);
        customAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem menuItem = menu.findItem(R.id.remove);
        SubMenu subMenu = menuItem.getSubMenu();
        subMenu.clear();
        for(int i =0 ;i<SongTitle.size();i++){
            subMenu.add(0,i,i,SongTitle.get(i));
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.add:
                openDialog();
                break;
            case R.id.remove:
                break;
            case R.id.exit:
                finish();
                break;
            default:
                if(SongTitle.size()==1)
                    Toast.makeText(getApplicationContext(), "List cannot be empty", Toast.LENGTH_SHORT).show();
                else {
                    customAdapter.notifyDataSetChanged();
                    Artist.remove(id);
                    VideoUrl.remove(id);
                    WikiURL.remove(id);
                    SongTitle.remove(id);
                    ArtistURL.remove(id);

                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int index  = info.position;

        if(item.getTitle()=="Video"){
            Intent intent = new Intent(getApplicationContext(),WebPage.class);
            intent.putExtra("url",VideoUrl.get(index)) ;
            startActivity(intent);
        }

        if (item.getTitle()== "Description"){
            Intent intent = new Intent(getApplicationContext(),WebPage.class);
            intent.putExtra("url",WikiURL.get(index)) ;
            startActivity(intent);
        }

        if(item.getTitle()=="Artist"){
            Intent intent = new Intent(getApplicationContext(),WebPage.class);
            intent.putExtra("url",ArtistURL.get(index)) ;
            startActivity(intent);
        }

        return true;
    }
    public void openDialog(){
        AddDialog addDialog = new AddDialog();
        addDialog.show(getSupportFragmentManager(),"add dialog");
    }
    protected void onPause() {
        super.onPause() ;
        setResult(RESULT_OK) ;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.add(0, v.getId(), 0, "Video");
        menu.add(0, v.getId(), 0, "Description");
        menu.add(0, v.getId(), 0, "Artist");

    }
    class CustomAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return SongTitle.size();
        }
        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view=getLayoutInflater().inflate(R.layout.list,null);
            TextView title = (TextView)view.findViewById(R.id.title);
            TextView artist =(TextView)view.findViewById(R.id.artist);
            title.setText(SongTitle.get(i));
            artist.setText(Artist.get(i));
            return view;
        }
    }


}
