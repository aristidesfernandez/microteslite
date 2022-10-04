package co.com.ies.pagination.infrastructure.primary;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.v3.oas.annotations.media.Schema;
import co.com.ies.common.domain.Generated;
import co.com.ies.pagination.domain.MicrolitePageable;

@Schema(name = "MicrolitePageable", description = "Pagination information")
public class RestMicrolitePageable {

  private int page;
  private int pageSize = 10;

  @Generated
  public RestMicrolitePageable() {}

  public RestMicrolitePageable(int page, int pageSize) {
    this.page = page;
    this.pageSize = pageSize;
  }

  @Min(value = 0)
  @Schema(description = "Page to display (starts at 0)", example = "0")
  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  @Min(value = 1)
  @Max(value = 100)
  @Schema(description = "Number of elements on each page", example = "10")
  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public MicrolitePageable toPageable() {
    return new MicrolitePageable(page, pageSize);
  }
}
