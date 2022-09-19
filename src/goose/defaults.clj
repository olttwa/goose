(ns goose.defaults
  (:require
    [clojure.string :as string]))

(def long-polling-timeout-sec 1)
(def scheduled-jobs-pop-limit 50)
(def cron-names-pop-limit 50)
(def heartbeat-sleep-sec 15)
(def heartbeat-expire-sec 60)
(def content-type "ptaoussanis/nippy")

(def queue-prefix "goose/queue:")
(def in-progress-queue-prefix "goose/in-progress-jobs:")
(def process-prefix "goose/processes:")
(def heartbeat-prefix "goose/heartbeat:")

(def default-queue "default")
(def schedule-queue "scheduled-jobs")
(def dead-queue "dead-jobs")
(def cron-queue "cron-schedules")
(def cron-entries "cron-entries")

(def protected-queues [schedule-queue dead-queue cron-queue cron-entries])

(defn ^:no-doc prefix-queue
  [queue]
  (str queue-prefix queue))

(defn ^:no-doc affix-queue
  [queue]
  (-> queue
      (string/split (re-pattern (str queue-prefix "*")))
      (second)))

(def prefixed-schedule-queue (prefix-queue schedule-queue))
(def prefixed-retry-schedule-queue (prefix-queue schedule-queue))
(def prefixed-dead-queue (prefix-queue dead-queue))
(def prefixed-cron-queue (prefix-queue cron-queue))
(def prefixed-cron-entries (str "goose/" cron-entries))

(def redis-internal-thread-pool-size 4)
(def redis-default-url "redis://localhost:6379")
(def redis-client-pool-size 5)

(def rmq-default-url "amqp://guest:guest@localhost:5672")
(def rmq-exchange "")
(def rmq-delay-exchange (prefix-queue schedule-queue))
(def rmq-delay-exchange-type "x-delayed-message")
(def rmq-low-priority 0)
(def rmq-high-priority 1)
(def rmq-prefetch-limit 1)
(def classic-queue :classic)
(def quorum-queue :quorum)
(def sync-confirms :sync)
(def async-confirms :async)
(def rmq-delay-limit-ms 4294967295) ; (2^32 - 1)
