package com.chainx.musig;

import java.nio.Buffer;
import java.nio.CharBuffer;

public class CLibrary {
    static {
        System.loadLibrary("musig");
    }

    public static native String get_my_pubkey(String priv);

    public static native String get_my_privkey(String phrase);

    public static native CharBuffer get_musig(String priv);

    public static native String get_my_reveal(CharBuffer musig);

    public static native CharBuffer cosign_stage(CharBuffer musig, String reveals, String pubkeys);

    public static native String get_my_cosign(CharBuffer musig);

    public static native String get_signature(String reveals, String pubkeys, String cosigns);

    public static native String get_agg_pubkey(String pubkeys);

    public static native String generate_threshold_pubkey(String pubkeys, byte threshold);

    public static native String generate_control_block(String pubkeys, byte threshold, String sigAggPubkey);

    public static native String encode_reveal_stage(CharBuffer musig);

    public static native CharBuffer decode_reveal_stage(String musig);

    public static native String encode_cosign_stage(CharBuffer musig);

    public static native CharBuffer decode_cosign_stage(String musig);
}
