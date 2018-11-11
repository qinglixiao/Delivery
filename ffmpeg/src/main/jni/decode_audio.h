//
// Created by chunyu on 2018/11/9.
//

#include <libavcodec/avcodec.h>

#ifndef DELIVERY_DECODE_AUDIO_H
#define DELIVERY_DECODE_AUDIO_H

#endif //DELIVERY_DECODE_AUDIO_H

static void decode(AVCodecContext *dec_ctx, AVPacket *pkt, AVFrame *frame,
                   FILE *outfile);

int input(int argc, char **argv);