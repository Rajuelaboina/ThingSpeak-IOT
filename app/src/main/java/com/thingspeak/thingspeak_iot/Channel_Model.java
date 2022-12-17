package com.thingspeak.thingspeak_iot;

import java.util.List;

public class Channel_Model {
   Channel channel;
   List<Feeds> feeds;

   public Channel_Model( Channel channel, List<Feeds> feeds) {
      this.channel = channel;
      this.feeds = feeds;
   }

   public Channel getChannel() {
      return channel;
   }

   public List<Feeds> getFeeds() {
      return feeds;
   }


}
