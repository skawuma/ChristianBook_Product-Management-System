package com.inn.product.data;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.inn.product.entity.Products;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class ProcessEachUrlData {
//single thread
//    @Async
//    public void processEachUrl() {
//        System.out.println("========== Processing started =======================");
//        Map<String, Products> productsMap = new HashMap<>();
//        Map<String, String> urlMaps = Data.getUrlMap();
//        ProcessData pro = new ProcessData(new RestTemplate());
//        for (Map.Entry<String, String> entry : urlMaps.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            Data.getProductsMap().put(key, pro.getData(value));
//            System.out.println(Data.getProductsMap());
//        }
//        System.out.println("========== ********Processing end****** =======================");
////        Data.setProductsMap(productsMap);
//    }

    @Async
    public void processEachUrl() {
        long startTime = System.nanoTime();
        System.out.println("========== Processing started ======================="+startTime);
        Map<String, String> urlMaps = Data.getUrlMap();
//        System.out.println(urlMaps.size());
        int batchSize = 2; // You can adjust the batch size according to your requirements

        List<Map<String, String>> batches = splitMapIntoBatches(urlMaps, batchSize);

        List<CompletableFuture<Void>> futures = batches.stream()
                .map(this::processBatchUrlsAsync)
                .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("========== ********Processing end****** ======================="+executionTime);
    }

//    @Async
    private CompletableFuture<Void> processBatchUrlsAsync(Map<String, String> batch) {
        return CompletableFuture.runAsync(() -> processBatchUrls(batch));
    }

    private void processBatchUrls(Map<String, String> batch) {
        Map<String, Products> productsMap = new HashMap<>();
        ProcessData pro = new ProcessData(new RestTemplate());
//        System.out.println(Data.getProductsMap().size());
        for (Map.Entry<String, String> entry : batch.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            productsMap.put(key, pro.getData(value));
        }

        Data.getProductsMap().putAll(productsMap);
    }

    private List<Map<String, String>> splitMapIntoBatches(Map<String, String> map, int batchSize) {
        int totalSize = map.size();
        int batchCount = (totalSize + batchSize - 1) / batchSize;

        return map.entrySet()
                .stream()
                .collect(Collectors.groupingBy(entry -> (entry.getKey().hashCode() & Integer.MAX_VALUE) % batchCount))
                .values()
                .stream()
                .map(entries -> entries.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
                .collect(Collectors.toList());
    }

}
