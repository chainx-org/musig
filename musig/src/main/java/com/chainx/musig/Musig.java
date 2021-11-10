package com.chainx.musig;

import android.text.TextUtils;

public class Musig {
    static {
        System.loadLibrary("musig_dll");
    }

    public static native String get_my_pubkey(String priv);

    public static native String get_my_privkey(String phrase);

    public static native long get_musig(long message, String priv);

    public static native String get_my_reveal(long musig);

    public static native long cosign_stage(long musig, String reveals, String pubkeys);

    public static native String get_my_cosign(long musig);

    public static native String get_signature(long message, String reveals, String pubkeys, String cosigns);

    public static native String get_agg_pubkey(String pubkeys);

    public static native String encode_reveal_stage(long musig);

    public static native long decode_reveal_stage(String musig);

    public static native String encode_cosign_stage(long musig);

    public static native long decode_cosign_stage(String musig);

    public static long getMusig(long message, String priv) {
        return get_musig(message, priv);
    }

    public static String getMyPubkey(String priv) {
        return get_my_pubkey(priv);
    }

    public static String getMyPrivkey(String phrase) {
        return get_my_privkey(phrase);
    }

    public static String getMyReveal(long musig) {
        return get_my_reveal(musig);
    }

    public static String getMyCosign(long musig, String[] reveals, String[] pubkeys) {
        musig = cosign_stage(musig, TextUtils.join("", reveals).toString(), TextUtils.join("", pubkeys).toString());
        return get_my_cosign(musig);
    }

    public static String encodeRevealStage(long musig) {
        return encode_reveal_stage(musig);
    }

    public static long decodeRevealStage(String musig) {
        return decode_reveal_stage(musig);
    }

    public static String encodeCosignStage(long musig) {
        return encode_cosign_stage(musig);
    }

    public static long decodeCosignStage(String musig) {
        return decode_cosign_stage(musig);
    }

    public static String getAggSignature(long message, String[] reveals, String[] cosigns, String[] pubkeys) {
        return get_signature(message, TextUtils.join("", reveals).toString(), TextUtils.join("", pubkeys).toString(), TextUtils.join("", cosigns).toString());
    }

    public static String getAggPublicKey(String[] pubkeys) {
        return get_agg_pubkey(TextUtils.join("", pubkeys).toString());
    }
}
