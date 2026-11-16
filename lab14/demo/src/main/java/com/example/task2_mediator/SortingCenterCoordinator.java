package com.example.task2_mediator;

import com.example.util.ConsoleLogger;
import java.util.ArrayList;
import java.util.List;

public class SortingCenterCoordinator implements SortingCenterMediator {
    private ConveyorBelt conveyorBelt;
    private WarehouseScale scale;
    private BarcodeScanner scanner;
    private DirectionSorter sorter;
    private LoadingZone loadingZone;

    private final List<String> pendingBags = new ArrayList<>();

    public void setConveyorBelt(ConveyorBelt conveyorBelt) {
        this.conveyorBelt = conveyorBelt;
        this.conveyorBelt.setMediator(this);
    }

    public void setScale(WarehouseScale scale) {
        this.scale = scale;
        this.scale.setMediator(this);
    }

    public void setScanner(BarcodeScanner scanner) {
        this.scanner = scanner;
        this.scanner.setMediator(this);
        ConsoleLogger.log("Coordinator", "До системи підключено сканер: [" + scanner.getDeviceName() + "]");
    }

    public void setSorter(DirectionSorter sorter) {
        this.sorter = sorter;
        this.sorter.setMediator(this);
    }

    public void setLoadingZone(LoadingZone loadingZone) {
        this.loadingZone = loadingZone;
        this.loadingZone.setMediator(this);
    }

    @Override
    public void notify(SortingDevice sender, String event, Object data) {
        ConsoleLogger.log("Coordinator", "Отримано подію '" + event + "' від [" + sender.getDeviceName() + "]");

        switch (event) {
            case "PARCEL_ON_BELT" -> {
                SortingParcel parcel = (SortingParcel) data;
                scale.weigh(parcel);
            }
            case "WEIGHT_OK" -> {
                SortingParcel parcel = (SortingParcel) data;
                scanner.scan(parcel);
            }
            case "OVERWEIGHT" -> {
                SortingParcel parcel = (SortingParcel) data;
                ConsoleLogger.log("Coordinator", "Правило безпеки: перевантаження (" + parcel.getWeightKg() + " кг) -> команда зупинити стрічку.");
                conveyorBelt.stop();
            }
            case "SCANNED" -> {
                SortingParcel parcel = (SortingParcel) data;
                ConsoleLogger.log("Coordinator", "Правило маршрутизації: передача напрямку «" + parcel.getDestination() + "» сортувальнику.");
                sorter.routeParcel(parcel);
            }
            case "SORTED" -> {
                SortingParcel parcel = (SortingParcel) data;
                ConsoleLogger.log("Coordinator", "Посилку " + parcel.getTrackingNumber() + " сформовано у мішок напрямку «" + parcel.getDestination() + "».");
            }
            case "REQUEST_LOADING" -> {
                String bagCode = (String) data;
                if (!loadingZone.isVehicleDocked()) {
                    ConsoleLogger.log("Coordinator", "Правило рампи: машина відсутня -> відмова у завантаженні [" + bagCode + "].");
                    pendingBags.add(bagCode);
                    loadingZone.denyLoading(bagCode);
                } else {
                    ConsoleLogger.log("Coordinator", "Правило рампи: машина на місці -> дозвіл на завантаження [" + bagCode + "].");
                    loadingZone.allowLoading(bagCode);
                }
            }
            case "VEHICLE_DOCKED" -> {
                String plate = (String) data;
                ConsoleLogger.log("Coordinator", "Машину [" + plate + "] зафіксовано -> запуск завантаження очікуючих мішків (" + pendingBags.size() + " шт.).");
                for (String bag : pendingBags) {
                    loadingZone.allowLoading(bag);
                }
                pendingBags.clear();
            }
        }
    }
}