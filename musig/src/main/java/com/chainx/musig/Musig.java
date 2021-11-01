package com.chainx.musig;

import android.text.TextUtils;

import java.nio.CharBuffer;

public class Musig {

    public static CharBuffer getMusig(String priv) {
        return CLibrary.get_musig(priv);
    }

    public static String getMyPubkey(String priv) {
        return CLibrary.get_my_pubkey(priv);
    }

    public static String getMyPrivkey(String phrase) {
        return CLibrary.get_my_privkey(phrase);
    }

    public static String getMyReveal(CharBuffer musig) {
        return CLibrary.get_my_reveal(musig);
    }

    public static String getMyCosign(CharBuffer musig, String[] reveals, String[] pubkeys) {
        musig = CLibrary.cosign_stage(musig, TextUtils.join("", reveals).toString(), TextUtils.join("", pubkeys).toString());
        return CLibrary.get_my_cosign(musig);
    }

    public static String encodeRevealStage(CharBuffer musig) {
        return CLibrary.encode_reveal_stage(musig);
    }

    public static CharBuffer decodeRevealStage(String musig) {
        return CLibrary.decode_reveal_stage(musig);
    }

    public static String encodeCosignStage(CharBuffer musig) {
        return CLibrary.encode_cosign_stage(musig);
    }

    public static CharBuffer decodeCosignStage(String musig) {
        return CLibrary.decode_cosign_stage(musig);
    }

    public static String getAggSignature(String[] reveals, String[] cosigns, String[] pubkeys) {
        return CLibrary.get_signature(TextUtils.join("", reveals).toString(), TextUtils.join("", pubkeys).toString(), TextUtils.join("", cosigns).toString());
    }

    public static String getAggPublicKey(String[] pubkeys) {
        return CLibrary.get_agg_pubkey(TextUtils.join("", pubkeys).toString());
    }
}
