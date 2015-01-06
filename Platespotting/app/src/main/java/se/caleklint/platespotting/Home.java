package se.caleklint.platespotting;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import se.caleklint.platespotting.database.DatabaseHandler;


public class Home extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        NavigationDrawerFragment  navigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        navigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                setTitle(R.string.title_home);
                break;
            case 2:
                setTitle(R.string.title_history);
                break;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            int section = getArguments().getInt(ARG_SECTION_NUMBER);
            switch (section) {
                case 1:
                    return createHomeView(inflater, container);
                case 2:
                    return createHistoryView(inflater, container);
            }
            return null;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((Home) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

        private View createHomeView(LayoutInflater inflater, ViewGroup container) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            Button spottedButton = (Button) rootView.findViewById(R.id.spotted_button);
            DatabaseHandler databaseHandler = new DatabaseHandler(getActivity());
            int nextPlate = databaseHandler.getNumberOfStoredPlates() +1;
            String nextPlateString = String.format(getString(R.string.spotted_button_text), nextPlate);
            spottedButton.setText(nextPlateString);
            return rootView;
        }

        private View createHistoryView(LayoutInflater inflater, ViewGroup container) {
            View rootView = inflater.inflate(R.layout.fragment_history, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.spotted_button);
            DatabaseHandler databaseHandler = new DatabaseHandler(getActivity());
            textView.setText(String.valueOf(databaseHandler.getNumberOfStoredPlates()));
            return rootView;
        }
    }

}
