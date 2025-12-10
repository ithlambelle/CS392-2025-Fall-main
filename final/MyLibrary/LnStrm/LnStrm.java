import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LnStrm<T> {
    private Supplier<LnStcn<T>> producer;

    public LnStrm() {
	this.producer = () -> new LnStcn<T>();
    }
    public LnStrm(T x0) {
	this.producer = () -> new LnStcn<T>(x0);
    }
    public LnStrm(Supplier<LnStcn<T>> supplier) {
	this.producer = supplier;
    }

    public LnStcn<T> eval0() {
	Supplier<LnStcn<T>> current = producer;
	producer = null;
	return current.get();
    }

    public LnStrm<T> append0(LnStrm<T> other) {
	return new LnStrm<T>(
	  () -> {
	      LnStcn<T> cell = this.eval0();
	      if (cell.consq()) {
		  return new LnStcn<T>(cell.hd(), cell.tl().append0(other));
	      } else {
		  return other.eval0();
	      }
	  }
        );
    }

    public void foritm0(Consumer<? super T> work) {
	LnStcn<T> cell = eval0();
	while (cell.consq()) {
	    work.accept(cell.hd());
	    cell = cell.tl().eval0();
	}
    }

    public boolean forall0(Predicate<? super T> pred) {
	LnStcn<T> cell = eval0();
	while (cell.consq()) {
	    if (!pred.test(cell.hd())) return false;
	    cell = cell.tl().eval0();
	}
	return true;
    }

    public LnStrm<T> filter0(Predicate<? super T> pred) {
	return new LnStrm<T>(
	  () -> {
	      LnStcn<T> cell = this.eval0();
	      while (cell.consq() && !pred.test(cell.hd())) {
		  cell = cell.tl().eval0();
	      }
	      if (cell.consq()) {
		  return new LnStcn<T>(cell.hd(), cell.tl().filter0(pred));
	      } else {
		  return new LnStcn<T>();
	      }
	  }
        );
    }
}
