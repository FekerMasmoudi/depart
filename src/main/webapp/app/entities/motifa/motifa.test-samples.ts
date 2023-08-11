import { IMotifa, NewMotifa } from './motifa.model';

export const sampleWithRequiredData: IMotifa = {
  id: '2d1e7cfb-f30c-4eac-b8a1-072833b056f5',
  decmotif: 33199,
};

export const sampleWithPartialData: IMotifa = {
  id: '8d38633b-3452-4c6b-afac-de1368f15f4a',
  decmotif: 65717,
  libmotif: 'West',
};

export const sampleWithFullData: IMotifa = {
  id: '8078b088-0ceb-482e-b71f-5bdff82cf2ac',
  decmotif: 25514,
  libmotif: 'solutions encryption',
};

export const sampleWithNewData: NewMotifa = {
  decmotif: 79861,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
