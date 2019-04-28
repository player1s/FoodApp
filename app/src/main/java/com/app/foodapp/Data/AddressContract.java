package com.app.foodapp.Data;

import android.provider.BaseColumns;

public final class AddressContract {
    private AddressContract() {}
    public static final class PetEntry implements BaseColumns {
        public final static String TABLE_NAME = "address";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_CITY_NAME ="city";
        public final static String COLUMN_STREET_NAME ="street";
        public final static String COLUMN_HOUSE_NUMBEBR ="number";

    }
}
