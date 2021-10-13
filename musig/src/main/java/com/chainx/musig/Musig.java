package com.chainx.musig;

import android.text.TextUtils;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class Musig {
    public String privkey;
    public String pubkey;
    public Pointer musig;

    public Musig(String priv) {
        privkey = priv;
        pubkey = clib.get_my_pubkey(priv);
        musig = clib.get_musig(priv);
    }

    public String getMyPubkey() {
        return pubkey;
    }

    public String getMyCosign(String[] reveals, String[] pubkeys) {
        musig = clib.cosign_stage(musig, TextUtils.join("", reveals).toString(), TextUtils.join("", pubkeys).toString());
        return clib.get_my_cosign(musig);
    }

    public static String getAggSignature(String[] reveals, String[] cosigns, String[] pubkeys) {
        return clib.get_signature(TextUtils.join("", reveals).toString(), TextUtils.join("", pubkeys).toString(), TextUtils.join("", cosigns).toString());
    }

    public static String getAggPublicKey(String[] pubkeys) {
        return clib.get_agg_pubkey(TextUtils.join("", pubkeys).toString());
    }

    final static CLibrary clib = (CLibrary) Native.load(
            "musig_dll",
            CLibrary.class);
}
