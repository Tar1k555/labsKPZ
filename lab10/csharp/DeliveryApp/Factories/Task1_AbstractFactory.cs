using System;

namespace DeliveryApp.Factories
{
    public interface IBox { string GetDescription(); }
    public interface IFiller { string GetDescription(); }
    public interface ILabel { string GetDescription(); }

    public interface IDeliveryFactory
    {
        IBox CreateBox();
        IFiller CreateFiller();
        ILabel CreateLabel();
    }

    public class RegularBox : IBox { public string GetDescription() => "Звичайна картонна коробка"; }
    public class RegularFiller : IFiller { public string GetDescription() => "Паперовий наповнювач"; }
    public class RegularLabel : ILabel { public string GetDescription() => "Стандартна маркувальна етикетка"; }

    public class RegularPackagingFactory : IDeliveryFactory
    {
        public IBox CreateBox() => new RegularBox();
        public IFiller CreateFiller() => new RegularFiller();
        public ILabel CreateLabel() => new RegularLabel();
    }

    public class FragileBox : IBox { public string GetDescription() => "Посилена коробка з захистом від ударів"; }
    public class FragileFiller : IFiller { public string GetDescription() => "Повітряно-бульбашкова плівка"; }
    public class FragileLabel : ILabel { public string GetDescription() => "Етикетка «КРИХКЕ / ОЕРЕЖЕМО»"; }

    public class FragilePackagingFactory : IDeliveryFactory
    {
        public IBox CreateBox() => new FragileBox();
        public IFiller CreateFiller() => new FragileFiller();
        public ILabel CreateLabel() => new FragileLabel();
    }

    public class ColdBox : IBox { public string GetDescription() => "Термоконтейнер з ізоляцією"; }
    public class ColdFiller : IFiller { public string GetDescription() => "Акумулятор холоду (гель)"; }
    public class ColdLabel : ILabel { public string GetDescription() => "Етикетка «ТЕМПЕРАТУРНИЙ РЕЖИМ +2...+8°C»"; }

    public class ColdPackagingFactory : IDeliveryFactory
    {
        public IBox CreateBox() => new ColdBox();
        public IFiller CreateFiller() => new ColdFiller();
        public ILabel CreateLabel() => new ColdLabel();
    }

    public class OversizedBox : IBox { public string GetDescription() => "Велика дерев'яна обрешітка"; }
    public class OversizedFiller : IFiller { public string GetDescription() => "Побутовий демпферний ущільнювач"; }
    public class OversizedLabel : ILabel { public string GetDescription() => "Етикетка «НЕГАБАРИТНИЙ ВАНТАЖ»"; }

    public class OversizedPackagingFactory : IDeliveryFactory
    {
        public IBox CreateBox() => new OversizedBox();
        public IFiller CreateFiller() => new OversizedFiller();
        public ILabel CreateLabel() => new OversizedLabel();
    }

    public class PackagingDepartment
    {
        public void PackShipment(IDeliveryFactory factory, string shipmentName)
        {
            IBox box = factory.CreateBox();
            IFiller filler = factory.CreateFiller();
            ILabel label = factory.CreateLabel();

            Console.WriteLine($"[10:00] [PackagingDept] Запаковано «{shipmentName}»:");
            Console.WriteLine($"  - Коробка: {box.GetDescription()}");
            Console.WriteLine($"  - Наповнювач: {filler.GetDescription()}");
            Console.WriteLine($"  - Етикетка: {label.GetDescription()}");
        }
    }
}
