export interface IItineraire {
  id: string;
  deccent?: number | null;
  decagenc?: number | null;
  denumli?: string | null;
  decstat?: string | null;
  denumlg?: number | null;
  dekmsta?: number | null;
  dedurtr?: number | null;
  deescale?: number | null;
  embra?: string | null;
  section?: number | null;
  sens?: string | null;
  dectyst?: string | null;
}

export type NewItineraire = Omit<IItineraire, 'id'> & { id: null };
