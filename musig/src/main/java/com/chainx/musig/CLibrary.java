package com.chainx.musig;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

public interface CLibrary extends Library {
    public String get_my_pubkey(String priv);

    public Pointer get_musig(String priv);

    public String get_my_reveal(Pointer musig);

    public Pointer cosign_stage(Pointer musig, String reveals, String pubkeys);

    public String get_my_cosign(Pointer musig);

    public String get_signature(String reveals, String pubkeys, String cosigns);

    public String get_agg_pubkey(String pubkeys);

    public String generate_threshold_pubkey(String pubkeys, byte threshold);

    public String generate_control_block(String pubkeys, byte threshold, String sigAggPubkey);

    public String encode_reveal_stage(Pointer musig);

    public Pointer decode_reveal_stage(String musig);

    public String encode_cosign_stage(Pointer musig);

    public Pointer decode_cosign_stage(String musig);
}
