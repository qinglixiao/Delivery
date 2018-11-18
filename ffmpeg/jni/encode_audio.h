//
// Created by chunyu on 2018/11/9.
//

#include <cstdio>
#include <libavcodec/avcodec.h>

#ifndef DELIVERY_ENCODE_AUDIO_H
#define DELIVERY_ENCODE_AUDIO_H

#endif //DELIVERY_ENCODE_AUDIO_H

static void encode(AVCodecContext *ctx, AVFrame *frame, AVPacket *pkt,
                   FILE *output);

int output(int argc, char **argv);