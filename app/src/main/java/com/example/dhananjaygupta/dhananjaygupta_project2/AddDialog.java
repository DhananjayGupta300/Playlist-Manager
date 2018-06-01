package com.example.dhananjaygupta.dhananjaygupta_project2;

import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
public class AddDialog extends AppCompatDialogFragment {
    EditText UrlWiki;
    EditText UrlVideo;
    EditText SongTitle;
    EditText SongArtist;
    EditText ArtistURL;

    private DialogAddingListener listener;
    public interface DialogAddingListener{
        void songAdding(String title, String song, String artist, String wikiurl, String videourl);
    }
    @Override
    public Dialog onCreateDialog(final Bundle savedInstance){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_add_dialog,null);
        SongTitle = (EditText) view.findViewById(R.id.Songtitle);
        SongArtist=(EditText) view.findViewById(R.id.ArtistName);
        ArtistURL=(EditText) view.findViewById(R.id.ArtistURL);
        UrlWiki=(EditText) view.findViewById(R.id.WikiURL);
        UrlVideo=(EditText) view.findViewById(R.id.VideoURL);

        builder.setView(view).setTitle("Add")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Add Here!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = SongTitle.getText().toString();
                        String artist = SongArtist.getText().toString();
                        String artisturl = ArtistURL.getText().toString();
                        String wikiurl = SongArtist.getText().toString();
                        String videourl = UrlVideo.getText().toString();
                        listener.songAdding(title,artist,artisturl,wikiurl,videourl);
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DialogAddingListener)context;
        } catch (ClassCastException e) {
            throw new  ClassCastException(context.toString()+"DialogAddingListener must be implemented");
        }
    }
}
