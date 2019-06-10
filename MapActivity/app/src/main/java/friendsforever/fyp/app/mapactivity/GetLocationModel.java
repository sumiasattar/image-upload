package friendsforever.fyp.app.mapactivity;

/**
 * Created by usama on 5/27/2019.
 */

class GetLocationModel {
    public double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public GetLocationModel(double mLatitude, double mLongitude) {
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
    }

    double mLatitude;

    public GetLocationModel() {
    }

    double mLongitude;
}
