package com.vj.CountryLocator;
// ---------Welcome to Vjworld----------
//ThankYou
        import org.geotools.data.DataStore;
        import org.geotools.data.DataStoreFinder;
        import org.geotools.data.simple.SimpleFeatureCollection;
        import org.geotools.data.simple.SimpleFeatureIterator;
        import org.geotools.feature.FeatureIterator;
        import org.geotools.geometry.jts.JTSFactoryFinder;
        import org.locationtech.jts.geom.Coordinate;
        import org.locationtech.jts.geom.Geometry;
        import org.locationtech.jts.geom.Point;
        import org.opengis.feature.simple.SimpleFeature;

        import java.io.File;
        import java.util.HashMap;
        import java.util.Map;

public class CountryLocator {
    private static final String SHAPEFILE_PATH = "private static final String SHAPEFILE_PATH = \"C:\\\\Users\\\\virug\\\\Downloads\\\\CountryLocator\\\\CountryLocator\\\\admin_0\\\\ne_10m_admin_0_countries.shp\";\n";
    private static final String ISO_A2_ATTRIBUTE = "ISO_A2";
    private SimpleFeatureCollection features;

    public CountryLocator() throws Exception {
        Config config = new Config();
        String appName = config.getProperty("spring.application.name");
        System.out.println("Application Name: " + appName);

        File file = new File(SHAPEFILE_PATH);
        Map<String, Object> map = new HashMap<>();
        map.put("url", file.toURI().toURL());

        DataStore dataStore = DataStoreFinder.getDataStore(map);
        String typeName = dataStore.getTypeNames()[0];

        features = dataStore.getFeatureSource(typeName).getFeatures();
    }

    public String getCountryCode(double latitude, double longitude) throws Exception {
        org.locationtech.jts.geom.GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

        Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));

        try (SimpleFeatureIterator iterator = features.features()) {
            while (iterator.hasNext()) {
                SimpleFeature feature = iterator.next();
                Geometry geometry = (Geometry) feature.getDefaultGeometry();
                if (geometry.contains(point)) {
                    return (String) feature.getAttribute(ISO_A2_ATTRIBUTE);
                }
            }
        }

        throw new Exception("Country not found ");
    }

    public void close() {

    }
}


