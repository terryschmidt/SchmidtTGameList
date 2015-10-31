package com.transitiontose.schmidttgamelist;

import com.transitiontose.schmidttgamelist.Game.Game;
import com.transitiontose.schmidttgamelist.Game.GameList;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.app.Activity;
import android.app.FragmentManager;
import android.widget.ImageView;

/**
 * A fragment representing a single Game detail screen.
 * This fragment is either contained in a {@link GameListActivity}
 * in two-pane mode (on tablets) or a {@link GameDetailActivity}
 * on handsets.
 */
public class GameDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Game game;

    public interface DetailCallbacks {
        public void onItemChanged();
    }

    private DetailCallbacks mCallbacks;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GameDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            game = GameList.GAME_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (game != null) {
            TextView name = (TextView) rootView.findViewById(R.id.text1);
            TextView description = (TextView) rootView.findViewById(R.id.text2);
            ImageView icon = (ImageView) rootView.findViewById(R.id.image);

            name.setText(game.getName());
            description.setText(game.getLongDescription());
            icon.setImageResource(Game.getIconResource(game.getCover()));
        }

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        FragmentManager fragmentManager = activity.getFragmentManager();
        Fragment wineListFragment	= fragmentManager.findFragmentById(R.id.game_list);
        if (wineListFragment instanceof DetailCallbacks) {
            mCallbacks = (DetailCallbacks) wineListFragment;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mCallbacks = null;
    }
}
