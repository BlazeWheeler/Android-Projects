package com.nletechnologies.smartmirrorapp1;

import android.widget.ImageView;

public class WeatherImage {
    public static void setImageForValue(int value, ImageView imageView, int isDay) {
        int imageId;

        switch (value) {
            case 0:
                if (isDay == 0 )
                    imageId = R.drawable.polyclearskynigh;
                 else
                    imageId = R.drawable.polyclearsky;
                break;
            case 1:
                if (isDay == 0 )
                    imageId = R.drawable.polyclearskynigh;
                else
                    imageId = R.drawable.polymainlyclearday;
                break;
            case 2:
                if (isDay == 0 )
                    imageId = R.drawable.polycloudynight;
                else
                    imageId = R.drawable.polymainlyclearday;

                break;

            case 3:
                imageId = R.drawable.polyovercast;
                break;
            // Add more cases for other integer values

            default:
                imageId = R.drawable.clearsky;
                break;
        }

        imageView.setImageResource(imageId);
    }


}
