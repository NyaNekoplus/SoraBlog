<template>
  <div id="aplayer">

  </div>
</template>

<script>
import APlayer from 'aplayer';
export default {
  name: "Mplayer",
  data: () => ({}),
  mounted() {
    const ap = new APlayer({
      container: document.getElementById('aplayer'),
      fixed: true,
      //mini: false, // 导致默认打开
      //autoplay: false,
      theme: '#FADFA3',
      //loop: 'all',
      //order: 'random',
      preload: 'auto',
      volume: 0.7,
      mutex: true,
      listFolded: false,
      listMaxHeight: 90,
      //lrcType: 3,
      audio: [
        {
          name: '小学生でも安心で聞ける子供向けのソングの一つ',
          artist: 'うんこ',
          url: 'https://image.sora.vin/music/syogakuse.mp3',
          cover: 'https://image.sora.vin/music/yyut.JPG',
          //lrc: 'lrc1.lrc',
          theme: '#ebd0c2'
        },
        {
          name: '一般幼児安眠シリーズ',
          artist: 'うんこ',
          url: 'https://image.sora.vin/music/youji.mp3',
          cover: 'https://image.sora.vin/music/yyut.JPG',
          //lrc: 'lrc1.lrc',
          theme: '#ebd0c2'
        },
        {
          name: 'Practise love',
          artist: 'JJ Lin',
          url: 'https://image.sora.vin/music/practise-love.flac',
          cover: 'https://image.sora.vin/music/practise-love.jpg',
          //lrc: 'lrc1.lrc',
          theme: '#ebd0c2'
        },
        {
          name: 'ダイヤモンドの純度(off vocal)',
          artist: 'Yukinoshita',
          url: 'https://image.sora.vin/music/daiyamondo.flac',
          cover: 'https://image.sora.vin/music/daiyamondo.jpg',
          //lrc: 'lrc1.lrc',
          theme: '#ebd0c2'
        },
        {
          name: 'あの頃のように',
          artist: 'Shinnya Ishigawa',
          url: 'https://image.sora.vin/music/anokoronoyouni.flac',
          cover: 'https://image.sora.vin/music/ano.jpg',
          //lrc: 'lrc1.lrc',
          theme: '#ebd0c2'
        },
        {
          name: '膝枕で過ごす放課後',
          artist: 'Manack',
          url: 'https://image.sora.vin/music/Hizamakuradesugosuhoukago.flac',
          cover: 'https://image.sora.vin/music/sora.jpg',
          //lrc: 'lrc2.lrc',
          theme: '#46718b'
        }
      ]
    });
  }
}
</script>

<style scoped>
/*@import "aplayer/dist/APlayer.min.css";*/

</style>