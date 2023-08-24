package com.luv2code.springbootlibrary.utils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Guillaume COLLET
 */
public class ExtractJWT {

    public static String payloadJWTExtraction(String token, String extraction) {

/**
 * By default, a token comes with the word "Bearer" and a space as a prefix,
 * so here, we replace this prefix by an empty string to keep only, the JWT content;
 */
        token.replace("Bearer ", "");

/**
 * Here, the idea is to split the token into pieces according to a period (which is the dot .)
 * --> 3 parts/pieces in a JWT :
 *  - header (example) : eyJraWQiOiIxUEc4dExva2tfak4xeWg4eE80SGVoS1RZc3U3S1d1N1NGdmpHM2NQXzNNIiwiYWxnIjoiUlMyNTYifQ
 *  - payload (example) : eyJ2ZXIiOjEsImp0aSI6IkFULjlQMXpmQlVPWHJqWmpScEZlMWlYc2pRZENKcVJYNC1ZOG5RQWh6OVhhZzAiLCJpc3MiOiJodHRwczovL2Rldi0zNTc1MTE2Ni5va3RhLmNvbS9vYXV0aDIvZGVmYXVsdCIsImF1ZCI6ImFwaTovL2RlZmF1bHQiLCJpYXQiOjE2ODczNTU3NTYsImV4cCI6MTY4NzM1OTM1NiwiY2lkIjoiMG9hOXZldWFmNHFzVDJua2I1ZDciLCJ1aWQiOiIwMHU5dmRsZmtmQ3FYM1hVdzVkNyIsInNjcCI6WyJwcm9maWxlIiwiZW1haWwiLCJvcGVuaWQiXSwiYXV0aF90aW1lIjoxNjg3MzU0ODk0LCJzdWIiOiJndWlsbGF1bWUuY29sbGV0QGFjLXJlbm5lcy5mciJ9
 *  - signature (example) : og39Bx9RG-KZKikfCBLcY903-R9ufJK1sEaox5Q0X0ouktD1YsHJadqptRg6MulwCTz23bt1ve70p02IST8m9r9sggqMsV-wHOQBGHyoX6sRxPZZn4xXcosRTbebUHlG7WC4cBFU6sx42ySseZDUeyJZGjyeMfqn-HwQipFMVvQaRi_Vc-AmHIAVYvqP8oO66WWJNKdOl_vhom3Yjpubp1dI0Ig7t6JOYzIq5DKCss3kyea25lVpqNA2Stt15AiGfKRRdJTfN31oKq8rorbgl5SyuTUHf7LwD7VdScWgmqcPU-TU5GuiMUSVmyuaHE_4kGmS5CV2TTQUADHLM54gWA
 *
 *  ==> JWT global structure : header.payload.signature
 */
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        // To decode the payload (ie the second element in the array above)
        String payload = new String(decoder.decode(chunks[1]));

        /**
         * Here, we want to split all the entries contained into the payload
         * and that are separated with commas;
         */
        String[] entries = payload.split(",");

        Map<String, String> map = new HashMap<String, String>();

        /**
         * A foreach loop to split each entry into a K/V pair and pull out the sub entry
         */
        for (String entry : entries) {
            String[] keyValue = entry.split(":");
            if (keyValue[0].equals(extraction)) {
                int remove = 1;

                if (keyValue[1].endsWith("}")) {
                    remove = 2;
                }

                /**
                 * To clean up the value of the "sub" key of any unwanted characters (", }) :
                 * - First step :
                 *      - extract a sub-string from the 1st character,
                 *      over a length = length of the string minus 2 (see condition above --> })
                 * */
                keyValue[1] = keyValue[1].substring(0, keyValue[1].length() - remove);

                /**
                 * - Second step :
                 *      - from this previous result, extract a sub-string from the second character
                 *      (in this case we exclude the 1st "), keeping the entire remaining length.
                 * */
                keyValue[1] = keyValue[1].substring(1);

                /**
                 * Inject the "sub" key and its cleaned value in the map;
                 */
                map.put(keyValue[0], keyValue[1]);
            }
        }

        /**
         * To finish, if map contains the wanted key (sub here), returns the value of this key;
         * */
        if (map.containsKey(extraction)) {
            return map.get(extraction);
        }

        /**
         * If not, returns null;
         * */
        return null;
    }
}
