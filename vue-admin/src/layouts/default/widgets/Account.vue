<template>
  <v-menu bottom left offset-y origin="top right" transition="scale-transition"><!-- min-width="200"-->
    <template v-slot:activator="{ on }">
      <v-btn
        icon
        x-large
        v-on="on"
      >
        <v-avatar tile size="48">
          <v-img class="white--text headline" :src="avatar"></v-img>
        </v-avatar>
      </v-btn>
    </template>
    <!--
    <template v-slot:activator="{ attrs, on }">
      <v-btn text v-bind="attrs" v-on="on">
        <v-avatar tile>
          <v-img :src="avatar"></v-img>
        </v-avatar>

      </v-btn>
    </template>
    -->
    <v-list :tile="false" flat nav>
      <app-bar-item>
        <h3>{{ username }}</h3>
      </app-bar-item>

      <app-bar-item>
        <v-btn depressed rounded text>Profile</v-btn>
      </app-bar-item>
      <v-divider class="my-3"></v-divider>
      <app-bar-item>
        <v-btn depressed rounded text @click="logout">Log out</v-btn>
      </app-bar-item>
    </v-list>
    <!--
  <v-list :tile="false" flat nav>
    <app-bar-item @click="logout">
      <v-list-item-title>Profile</v-list-item-title>
    </app-bar-item>
    <app-bar-item @click="logout">
      <v-list-item-title>Settings</v-list-item-title>
    </app-bar-item>
    <v-divider/>
    <app-bar-item>
      <v-list-item-title @click="logout">Log out</v-list-item-title>
    </app-bar-item>


    <template v-for="(p, i) in profile">
      <v-divider v-if="p.divider" :key="`divider-${i}`" class="mb-2 mt-2"/>

      <app-bar-item v-else :key="`item-${i}`" to="/">
        <v-list-item-title v-text="p.title" />
      </app-bar-item>
    </template>

    </v-list>
        -->
  </v-menu>
</template>

<script>
import {get, sync} from 'vuex-pathify';
  export default {
    name: 'DefaultAccount',

    data: () => ({
      profile: [
        { title: 'Profile' },
        { title: 'Settings' },
        { divider: true },
        { title: 'Log out' },
      ],
    }),
    methods: {
      logout(){
        this.$store.dispatch('logger/Logout').then(response => {
          if (response.state === this.$STATE.SUCCESS){
            console.log(response.message)
            location.reload();
          }else {
            console.log(response.message)
          }
        }).catch(() => {
          console.log('登出失败')
        })
      }
    },
    computed: {
      ...get('logger', [
        'username',
        'avatar',
        'proxy'
      ])
    }
  }
</script>
