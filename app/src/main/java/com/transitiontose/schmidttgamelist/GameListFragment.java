package com.transitiontose.schmidttgamelist;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.BaseAdapter;
import com.transitiontose.schmidttgamelist.Game.Game;
import com.transitiontose.schmidttgamelist.Game.GameList;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
/**
 * A list fragment representing a list of Games. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link GameDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class GameListFragment extends ListFragment implements GameDetailFragment.DetailCallbacks {

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(String id);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GameListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: replace with a real list adapter.
        setListAdapter(new GameAdapter(getActivity()));


    }



    @Override
    public void onResume() {
        super.onResume();
        ((GameAdapter) getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onItemChanged() {
        ((GameAdapter) getListAdapter()).notifyDataSetChanged();
    }

    class GameAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private Map<Game.Cover, Bitmap> icons;

        GameAdapter(Context context) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            icons = new HashMap<Game.Cover, Bitmap>();
            icons.put(Game.Cover.castlevania, BitmapFactory.decodeResource(context.getResources(), R.drawable.castlevania));
            icons.put(Game.Cover.contra, BitmapFactory.decodeResource(context.getResources(), R.drawable.contra));
            icons.put(Game.Cover.doubledragon2, BitmapFactory.decodeResource(context.getResources(), R.drawable.doubledragon2));
            icons.put(Game.Cover.duckhunt, BitmapFactory.decodeResource(context.getResources(), R.drawable.duckhunt));
            icons.put(Game.Cover.jackal, BitmapFactory.decodeResource(context.getResources(), R.drawable.jackal));
            icons.put(Game.Cover.kirby, BitmapFactory.decodeResource(context.getResources(), R.drawable.kirby));
            icons.put(Game.Cover.kungfu, BitmapFactory.decodeResource(context.getResources(), R.drawable.kungfu));
            icons.put(Game.Cover.loderunner, BitmapFactory.decodeResource(context.getResources(), R.drawable.loderunner));
            icons.put(Game.Cover.mariobros, BitmapFactory.decodeResource(context.getResources(), R.drawable.mariobros));
            icons.put(Game.Cover.mariobros2, BitmapFactory.decodeResource(context.getResources(), R.drawable.mariobros2));
            icons.put(Game.Cover.metroid, BitmapFactory.decodeResource(context.getResources(), R.drawable.metroid));
            icons.put(Game.Cover.supertecmobowl, BitmapFactory.decodeResource(context.getResources(), R.drawable.supertecmobowl));
            icons.put(Game.Cover.tetris, BitmapFactory.decodeResource(context.getResources(), R.drawable.tetris));
            icons.put(Game.Cover.zelda, BitmapFactory.decodeResource(context.getResources(), R.drawable.zelda));
            icons.put(Game.Cover.megaman, BitmapFactory.decodeResource(context.getResources(), R.drawable.megaman));
            icons.put(Game.Cover.megaman2, BitmapFactory.decodeResource(context.getResources(), R.drawable.megaman2));
        }

        @Override
        public int getCount() {
            return GameList.GAMES.size();
        }

        @Override
        public Object getItem(int i) {
            return GameList.GAMES.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            View row = convertView;
            if (row == null) {
                row = inflater.inflate(R.layout.game_list_item, parent, false);
                holder = new ViewHolder();
                holder.icon = (ImageView) row.findViewById(R.id.image);
                holder.name = (TextView) row.findViewById(R.id.text1);
                holder.description = (TextView) row.findViewById(R.id.text2);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            Game game = GameList.GAMES.get(position);
            holder.name.setText(game.getName());
            holder.description.setText(game.getShortDescription());
            holder.icon.setImageBitmap(icons.get(game.getCover()));
            return row;
        }


    }

    static class ViewHolder {
        TextView name;
        TextView description;
        ImageView icon;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        mCallbacks.onItemSelected(GameList.GAMES.get(position).getName());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }
}
