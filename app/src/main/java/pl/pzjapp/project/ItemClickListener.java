package pl.pzjapp.project;

import android.view.View;

public interface ItemClickListener {
    /**
     * @param view View of the current list item
     * @param position Position of the current list element
     * @param longClick Returns true in case of item long click
     */
    void onItemClick(View view, int position, boolean longClick);
}
