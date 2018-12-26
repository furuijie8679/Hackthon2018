package ghost.svc.utils;

import com.google.common.geometry.S2CellId;
import com.google.common.geometry.S2LatLng;

public class GeoUtils {
    private static final int DEFAULT_LEVEL = 15;

    public static String s2CellToken(final double lat, final double lng) {
        S2CellId s2CellId = S2CellId.fromPoint(S2LatLng.fromDegrees(lat, lng).toPoint());
        return s2CellId.parent(DEFAULT_LEVEL).toToken();
    }
}
