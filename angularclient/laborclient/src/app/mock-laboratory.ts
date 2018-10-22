import { LaboratoryResult } from "./entity/laboratoryResult";

export const RESULTS:LaboratoryResult[] = [{
    id: 1,
    valueA: 123,
    valueB: 456,
    valueC: "saldf",
    valueD: true,
    patient: {
      username: "patient1",
      authorities: undefined
    }
  },
  {
    id: 2,
    valueA: 321,
    valueB: 456,
    valueC: "saldf",
    valueD: false,
    patient: {
      username: "patient1",
      authorities: undefined
    }
  },
  {
    id: 3,
    valueA: 213,
    valueB: 34565,
    valueC: "saldf",
    valueD: true,
    patient: {
      username: "patient2",
      authorities: undefined
    }
  }];