package com.chainx.musig;

import android.text.TextUtils;

public class Mast {
    public static String generateThresholdPubkey(String[] pubkeys, byte threshold) {
        return CLibrary.generate_threshold_pubkey(TextUtils.join("", pubkeys).toString(), threshold);
    }

    public static String generateControlBlock(String[] pubkeys, byte threshold, String sigAggPubkey) {
        return CLibrary.generate_control_block(TextUtils.join("", pubkeys).toString(), threshold, sigAggPubkey);
    }
}
