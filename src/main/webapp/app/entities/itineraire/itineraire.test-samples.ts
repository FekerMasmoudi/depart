import { IItineraire, NewItineraire } from './itineraire.model';

export const sampleWithRequiredData: IItineraire = {
  id: '88265f32-5e65-4256-9ae7-51577548c2fa',
  decagenc: 14282,
  denumli: 'Advanced Home generation',
  decstat: 'mobile Savings',
  denumlg: 18856,
};

export const sampleWithPartialData: IItineraire = {
  id: 'b0544720-6d79-4c99-9686-4e0f2f7bdb60',
  deccent: 65173,
  decagenc: 30282,
  denumli: 'moderator Cambridgeshire',
  decstat: 'Legacy Concrete',
  denumlg: 80681,
  dekmsta: 12172,
  dectyst: 'Designer microchip',
};

export const sampleWithFullData: IItineraire = {
  id: 'df142e29-24dc-4d58-bab2-04701f41e1c9',
  deccent: 4503,
  decagenc: 61059,
  denumli: 'HTTP Licensed',
  decstat: 'forecast engineer',
  denumlg: 14354,
  dekmsta: 11788,
  dedurtr: 34186,
  deescale: 47495,
  embra: 'Intuitive Steel',
  section: 40288,
  sens: 'calculating array Developer',
  dectyst: 'payment Fantastic',
};

export const sampleWithNewData: NewItineraire = {
  decagenc: 14724,
  denumli: 'cyan',
  decstat: 'Legacy transform Tasty',
  denumlg: 94799,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
